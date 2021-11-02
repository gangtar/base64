import re

# 此方法用来将包含隐藏信息的字母转换为base64编码表对应的序列值（十进制数）并返回
def base64change(s):
    table = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/' # base64编码表，恰好索引值对应字母
    for i in range(len(table)):
        if table[i] == s:
            return i


# 此方法用来获取隐藏信息二进制字符串
def base64solve():
    f = open('C:\\Users\\admin\\Desktop\\stego.txt','r')
    lines = f.readlines()

    flag_bin = ''
    for line in lines:
        # print(line)
        l = line.strip()
        if l[-1] == '=':
            if l[-2] == '=':
                flag_bin += bin(base64change(l[-3]))[2:].zfill(4)[-4:]
            else:
                flag_bin += bin(base64change(l[-2]))[2:].zfill(2)[-2:]
    print(flag_bin)
    flag = ''
    for i in range(len(flag_bin)//8):
        flag += chr(int(flag_bin[i * 8:(i + 1) * 8], 2))
    print(flag)


if __name__ == '__main__':
    base64solve()
