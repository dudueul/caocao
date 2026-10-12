// name: StageJPN$$get_CDN_URL
// entry: 01af1098

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 StageJPN__get_CDN_URL(void)

{
  if ((bRam00000000049fbbdf & 1) == 0) {
    func_0x010c65d4(0xcb0c);
    bRam00000000049fbbdf = 1;
  }
  return _str_https___nxm_caocao_ww_akamaized_net;
}


