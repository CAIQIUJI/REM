import random
import math

'''设置感知节点数量，感知节点通信半径，插值区域x/y大小'''
def sig_distribution_gen(recv_node_num, d_min, x_max, y_max):
    bs_pos = [10, 10]
    #print("bs_pos", type(bs_pos))
    receive_nodes_map = receive_nodes_map_gen(recv_node_num, d_min, x_max, y_max)
    recv_nodes_info = []
    maxP = 0
    minP = 1000
    for node_pos in receive_nodes_map:
        sig_i = intensity_cal(bs_pos, node_pos)
        if sig_i > maxP:
            maxP = sig_i
        if sig_i < minP:
            minP = sig_i
        node_pos.append(sig_i)
        recv_nodes_info.append(node_pos)
    return recv_nodes_info, maxP, minP


'''产生基站位置'''
def base_station_pos_gen(x_max, y_max):
    x_max = x_max / 2
    y_max = y_max / 2
    x = random.uniform(-x_max, x_max)
    y = random.uniform(-y_max, y_max)
    pos = [x, y]
    return pos


'''产生sensor node位置'''
def receive_node_pos_gen(x_max, y_max):
    x_max = x_max / 2
    y_max = y_max / 2
    x = random.uniform(-x_max, x_max)
    y = random.uniform(-y_max, y_max)
    pos = [x, y]
    return pos



'''产生sensor node map集合'''
def receive_nodes_map_gen(recv_node_num, d_min, x_max, y_max):
    receive_nodes_map = []
    i = 0
    while i < recv_node_num:
        pos_ok = True
        new_pos = receive_node_pos_gen(x_max, y_max)
        for exist_pos in receive_nodes_map:
            d = distance_cal(exist_pos, new_pos)
            if d < d_min:
                pos_ok = False
                break
        if pos_ok:
            receive_nodes_map.append(new_pos)
            i = i + 1
            print(i * 100/recv_node_num, "%")
    return receive_nodes_map



'''计算两个位置之间的距离'''
def distance_cal(pos1, pos2):
    delta_x = pos1[0] - pos2[0]
    delta_y = pos1[1] - pos2[1]
    x = (delta_x ** 2 + delta_y ** 2) ** 0.5
    return x

'''sensor 接收能量计算'''
def intensity_cal(pos1, pos2):
    Ptx = 40
    kDB = 38
    y = 3
    d_0 = 200
    d_it = distance_cal(pos1, pos2)
    P_xi = Ptx + kDB - 10 * y * math.log(d_0, 10) + 10 * math.log(d_it ** (-1 * y), 10)
    P_gn = random.gauss(0, 1) * 3
    P_n = random.uniform(0, 8)
    P_xi = P_xi + P_gn
    return P_xi
