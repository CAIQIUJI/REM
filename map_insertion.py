from pykrige.ok import OrdinaryKriging
import matplotlib.pyplot as plt
import numpy as np
from time import *
import sig_distribution_gen as sdg


# def weight_node(data, point_pos):
#     local_node_num = 0
#     local_data_list = []
#     for i in range(data.shape[0]):
#         node_x = data[i, 0]
#         node_y = data[i, 1]
#         node_pos = [node_x, node_y]
#         dist = sdg.distance_cal(point_pos, node_pos)
#         a = 5
#         node_sig = data[i, 2] * a / (dist ** 2)
#         line = [node_x, node_y, node_sig]
#         local_data_list.append(line)
#         local_node_num = local_node_num + 1
#     local_data = np.array(local_data_list)
#     return local_data

'''选择局部节点进行分簇式克里金插值'''
def select_local_node(data, point_pos, dist):
    local_node_num = 0
    local_data_list = []
    for i in range(data.shape[0]):
        node_pos = [data[i, 0], data[i, 1]]
        if sdg.distance_cal(point_pos, node_pos) < dist:
            line = (data[i].tolist())
            local_data_list.append(line)
            local_node_num = local_node_num + 1
    local_data = np.array(local_data_list)
    return local_data

'''全局克里金插值'''
def global_krige(data, gridx, gridy):

    '''开始时间'''
    start_time = time()

    ok = OrdinaryKriging(data[:, 0], data[:, 1], data[:, 2], variogram_model='spherical',
                         verbose=False, enable_plotting=False)
    zz, ss = ok.execute('grid', gridx, gridy)

    '''结束时间'''
    end_time = time()
    run_time = end_time - start_time

    z = []
    for i in range(len(zz)):
        for j in range(len(zz[i])):
            z = z + [round(zz[i][j], 4)]
    #print("global z", z)
    return z, run_time

'''局部克里金插值'''
def local_krige(data, gridx, gridy, krige_range):
    z = []
    for i in range(len(gridx)):
        for j in range(len(gridy)):
            point_x = np.array([gridy[j]])
            point_y = np.array([gridx[i]])
            point_pos = [point_x, point_y]
            local_data = select_local_node(data, point_pos, krige_range)
            ok = OrdinaryKriging(local_data[:, 0], local_data[:, 1], local_data[:, 2], variogram_model='spherical',
                                 verbose=False, enable_plotting=False)
            zz, ss = ok.execute('grid', point_x, point_y)
            z = z + [round(zz[0][0], 4)]
        print("i, j: ", i, j)
    print("local z: ", z)
    return z

'''构建克里金插值后的REM图像'''
def drawPykrigePic(resolution, data, data_min, data_max, x_max, y_max, dst_path, pic_name, kMode, kRange = -1):
    #plt.ion() #开启interactive mode
    x_max = x_max / 2
    y_max = y_max / 2

    gridx = np.arange(-x_max - 0, x_max + resolution, resolution)
    gridy = np.arange(-y_max - 0, y_max + resolution, resolution)

    x = []
    for i in range(len(gridx)):
        for j in range(len(gridx)):
            x = x + [gridx[j]]

    y = []
    for i in range(len(gridy)):
        for j in range(len(gridy)):
            y = y + [gridy[i]]

    """color range"""
    levels = [data_min, (data_min + data_min) * 0.5, data_max]

    '''执行克里金插值法'''
    if kMode == "global":
        print("global mode")
        ret = global_krige(data, gridx, gridy)
        z = ret[0]
        run_time = ret[1]
    elif kMode == "local":
        print("local mode")
        z = local_krige(data, gridx, gridy, kRange)
    else:
        print("mode wrong!")
        exit(-1)

    plt.figure()
    ax = plt.gca()
    plt.xlim(-50, 50)
    plt.ylim(-50, 50)

    CS = ax.tricontourf(x, y, z, np.linspace(min(levels), max(levels), 256), cmap=plt.get_cmap('jet'))

    plt.xlabel('meter')
    plt.ylabel('meter')
    # plt.savefig(dst_path + "\\" + str(pic_name) + ".jpg")
    plt.savefig(dst_path + str(pic_name) + ".jpg")
    plt.show()
    plt.clf()
    plt.close('all')

    return run_time


