// name: System.IO.Util.FileUtil$$GetSalts
// entry: 01b10e10

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8
System_IO_Util_FileUtil__GetSalts(undefined8 param_1,undefined8 param_2,undefined4 param_3)

{
  undefined8 uVar1;
  long *plVar2;
  int iVar3;
  
  if ((bRam00000000049fbd24 & 1) == 0) {
    func_0x010c65d4(0x56d2);
    bRam00000000049fbd24 = 1;
  }
  uVar1 = func_0x01101ad4(_class_byte___TypeInfo,param_3);
  plVar2 = (long *)func_0x010fdda8(
                                  _class_System_Security_Cryptography_RNGCryptoServiceProvider_TypeInfo
                                  );
  System_Security_Cryptography_RNGCryptoServiceProvider___ctor(plVar2,0);
  iVar3 = 10;
  do {
    if (plVar2 == (long *)0x0) {
      func_0x010eec64(0);
    }
    (**(code **)(*plVar2 + 0x170))(plVar2,uVar1,*(undefined8 *)(*plVar2 + 0x178));
    iVar3 = iVar3 + -1;
  } while (iVar3 != 0);
  return uVar1;
}


