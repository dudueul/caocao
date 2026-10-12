// name: DevJPN$$get_CDN_URL
// entry: 023a408c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 DevJPN__get_CDN_URL(void)

{
  if ((bRam0000000004a007fd & 1) == 0) {
    func_0x010c65d4(0x37cb);
    bRam0000000004a007fd = 1;
  }
  return _str_https___nxm_caocao_jp_akamaized_net;
}


