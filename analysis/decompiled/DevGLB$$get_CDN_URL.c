// name: DevGLB$$get_CDN_URL
// entry: 023a3eac

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 DevGLB__get_CDN_URL(void)

{
  if ((bRam0000000004a007f7 & 1) == 0) {
    func_0x010c65d4(0x37c5);
    bRam0000000004a007f7 = 1;
  }
  return _str_https___nxm_caocao_ww_akamaized_net;
}


