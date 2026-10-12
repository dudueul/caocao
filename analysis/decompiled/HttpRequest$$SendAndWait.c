// name: HttpRequest$$SendAndWait
// entry: 016dd468

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long HttpRequest__SendAndWait
               (undefined8 param_1,undefined8 param_2,undefined8 param_3,byte param_4,
               undefined8 param_5,undefined8 param_6,undefined4 param_7,undefined8 param_8)

{
  long lVar1;
  
  if ((bRam00000000049f9848 & 1) == 0) {
    func_0x010c65d4(0x6ba8);
    bRam00000000049f9848 = 1;
  }
  lVar1 = func_0x010fdda8(_class_HttpRequest_<SendAndWait>c__Iterator1_TypeInfo);
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
    uRam0000000000000010 = param_2;
    func_0x010eec64(0);
    uRam0000000000000018 = param_3;
    func_0x010eec64(0);
    bRam0000000000000020 = param_4 & 1;
    func_0x010eec64(0);
    uRam0000000000000028 = param_6;
    func_0x010eec64(0);
    uRam0000000000000030 = param_7;
    func_0x010eec64(0);
    uRam0000000000000038 = param_8;
    func_0x010eec64(0);
    uRam0000000000000048 = param_5;
    func_0x010eec64(0);
  }
  else {
    *(undefined8 *)(lVar1 + 0x10) = param_2;
    *(undefined8 *)(lVar1 + 0x18) = param_3;
    *(undefined8 *)(lVar1 + 0x28) = param_6;
    *(undefined4 *)(lVar1 + 0x30) = param_7;
    *(undefined8 *)(lVar1 + 0x38) = param_8;
    *(byte *)(lVar1 + 0x20) = param_4 & 1;
    *(undefined8 *)(lVar1 + 0x48) = param_5;
  }
  *(undefined8 *)(lVar1 + 0x50) = param_1;
  return lVar1;
}


