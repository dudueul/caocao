// name: LiveJPN$$get_CDN_URL
// entry: 01894844

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 LiveJPN__get_CDN_URL(void)

{
  if ((bRam00000000049fa4c8 & 1) == 0) {
    func_0x010c65d4(0x846c);
    bRam00000000049fa4c8 = 1;
  }
  return _str_https___nxm_caocao_ww_akamaized_net;
}


