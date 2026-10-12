// name: ServerSetting$$get_instance
// entry: 015c7018

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void ServerSetting__get_instance(void)

{
  ulong uVar1;
  undefined8 uVar2;
  ulong uVar3;
  
  if ((bRam00000000049f8bda & 1) == 0) {
    func_0x010c65d4(0xbfed);
    bRam00000000049f8bda = 1;
  }
  if (((*(byte *)(_class_ServerSetting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_ServerSetting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if (**(long **)(_class_ServerSetting_TypeInfo + 0xb8) == 0) {
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar1 = Util__GetCountryCode(0,0);
    uVar3 = uVar1 & 0xffffffff;
    if (((*(byte *)(_class_ServerSetting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_ServerSetting_TypeInfo + 0xd8) == 0)) {
      uVar1 = func_0x010da55c(_class_ServerSetting_TypeInfo);
    }
    uVar2 = ServerSetting__GetServerSetting(uVar1,uVar3,4);
    **(undefined8 **)(_class_ServerSetting_TypeInfo + 0xb8) = uVar2;
  }
  return;
}


