// name: StageJPN$$get_GAME_SERVER_HOST_URL
// entry: 01af1050

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 StageJPN__get_GAME_SERVER_HOST_URL(void)

{
  if ((bRam00000000049fbbde & 1) == 0) {
    func_0x010c65d4(0xcb0d);
    bRam00000000049fbbde = 1;
  }
  return _str_http___ec2_13_230_87_231_ap_northeast_1_compute_amazonaws_com_9080;
}


