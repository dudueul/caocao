// name: StageJPN$$get_NAME
// entry: 01af1008

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 StageJPN__get_NAME(void)

{
  if ((bRam00000000049fbbdd & 1) == 0) {
    func_0x010c65d4(0xcb0f);
    bRam00000000049fbbdd = 1;
  }
  return _str_Stage;
}


