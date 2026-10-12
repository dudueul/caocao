// name: LiveGLB$$GetFallback
// entry: 01894524

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 LiveGLB__GetFallback(void)

{
  undefined8 uVar1;
  
  if ((bRam00000000049fa4be & 1) == 0) {
    func_0x010c65d4(0x8463);
    bRam00000000049fa4be = 1;
  }
  uVar1 = func_0x010fdda8(_class_ReviewGLB_TypeInfo);
  ReviewGLB___ctor(uVar1,0);
  return uVar1;
}


