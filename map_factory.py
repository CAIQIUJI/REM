import map_insertion as mi
import numpy as np
import sig_distribution_gen as sdg


run_time_array = []

'''循环出图'''
for node_num in range(400, 401, 50):
    '''每种配置生成多张图片'''
    for i in range(100):
        '''生成感知节点信息'''
        ret = np.array(sdg.sig_distribution_gen(node_num, 4, 100, 100))
        data = np.array(ret[0])

        '''全局插值图'''
        # ret = mi.drawPykrigePic(1, data, ret[1], ret[2], 100, 100,
        #                         "C:\\Users\\Jin\\Desktop\\krigePics\\GlobalKrige_pics", str(node_num) + "_" + str(i),
        #                         "global")
        ret = mi.drawPykrigePic(1, data, ret[1], ret[2], 100, 100,
                                "D:\\PythonCodes\\rem\\Samples\\REM", str(node_num) + "_" + str(i),
                                "global")
        run_time_array.append(ret)

    print("Node Num =", node_num, "complete.")

    '''求全局克里金方法耗时的平均值'''
    arr_sum = 0
    for i in range(len(run_time_array)):
        arr_sum = arr_sum + run_time_array[i]
    arr_avr = arr_sum / len(run_time_array)
    print("run time of central mode krige: ", arr_avr, "s")

