// name: DevGLB$$get_NAME
// entry: 023a3e1c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 DevGLB__get_NAME(void)

{
  if ((bRam0000000004a007f5 & 1) == 0) {
    func_0x010c65d4(0x37c8);
    bRam0000000004a007f5 = 1;
  }
  return _str_Dev;
}


