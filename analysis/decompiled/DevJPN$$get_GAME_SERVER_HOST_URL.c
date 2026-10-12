// name: DevJPN$$get_GAME_SERVER_HOST_URL
// entry: 023a4044

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 DevJPN__get_GAME_SERVER_HOST_URL(void)

{
  if ((bRam0000000004a007fc & 1) == 0) {
    func_0x010c65d4(0x37cc);
    bRam0000000004a007fc = 1;
  }
  return _str_http___onestage_nexon_corp_9080;
}


