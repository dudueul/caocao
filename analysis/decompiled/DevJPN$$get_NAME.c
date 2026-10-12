// name: DevJPN$$get_NAME
// entry: 023a3ffc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 DevJPN__get_NAME(void)

{
  if ((bRam0000000004a007fb & 1) == 0) {
    func_0x010c65d4(0x37ce);
    bRam0000000004a007fb = 1;
  }
  return _str_Dev;
}


