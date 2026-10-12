// name: StageGLB$$get_NAME
// entry: 01af0e28

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 StageGLB__get_NAME(void)

{
  if ((bRam00000000049fbbd7 & 1) == 0) {
    func_0x010c65d4(0xcb09);
    bRam00000000049fbbd7 = 1;
  }
  return _str_Stage;
}


