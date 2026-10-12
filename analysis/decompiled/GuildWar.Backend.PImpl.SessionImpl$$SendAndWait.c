// name: GuildWar.Backend.PImpl.SessionImpl$$SendAndWait
// entry: 01b2dc44

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long GuildWar_Backend_PImpl_SessionImpl__SendAndWait
               (undefined8 param_1,undefined8 param_2,undefined8 param_3,byte param_4,
               undefined8 param_5,undefined8 param_6)

{
  long lVar1;
  
  if ((bRam00000000049fbe03 & 1) == 0) {
    func_0x010c65d4(0xc02e);
    bRam00000000049fbe03 = 1;
  }
  lVar1 = func_0x010fdda8(
                         _class_GuildWar_Backend_PImpl_SessionImpl_<SendAndWait>c__Iterator0_TypeInfo
                         );
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
    uRam0000000000000010 = param_2;
    func_0x010eec64(0);
    uRam0000000000000018 = param_6;
    func_0x010eec64(0);
    uRam0000000000000020 = param_3;
    func_0x010eec64(0);
    bRam0000000000000028 = param_4 & 1;
    func_0x010eec64(0);
    uRam0000000000000030 = param_5;
    func_0x010eec64(0);
  }
  else {
    *(undefined8 *)(lVar1 + 0x10) = param_2;
    *(undefined8 *)(lVar1 + 0x18) = param_6;
    *(undefined8 *)(lVar1 + 0x20) = param_3;
    *(byte *)(lVar1 + 0x28) = param_4 & 1;
    *(undefined8 *)(lVar1 + 0x30) = param_5;
  }
  *(undefined8 *)(lVar1 + 0x38) = param_1;
  return lVar1;
}


