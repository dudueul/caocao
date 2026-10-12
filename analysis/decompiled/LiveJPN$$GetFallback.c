// name: LiveJPN$$GetFallback
// entry: 01894758

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 LiveJPN__GetFallback(void)

{
  undefined8 uVar1;
  
  if ((bRam00000000049fa4c5 & 1) == 0) {
    func_0x010c65d4(0x846a);
    bRam00000000049fa4c5 = 1;
  }
  uVar1 = func_0x010fdda8(_class_ReviewJPN_TypeInfo);
  ReviewJPN___ctor(uVar1,0);
  return uVar1;
}


