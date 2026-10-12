// name: GuildWar.Backend.PImpl.SessionImpl$$get_userCnt
// entry: 01b30bb8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__get_userCnt(long param_1)

{
  undefined4 uVar1;
  undefined8 uVar2;
  
  if ((bRam00000000049fbdf1 & 1) == 0) {
    func_0x010c65d4(0xc03e);
    bRam00000000049fbdf1 = 1;
  }
  uVar2 = *(undefined8 *)(param_1 + 0x80);
  uVar1 = *(undefined4 *)(param_1 + 0x88);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  func_0x021275d4(&stack0xffffffffffffffe0,uVar2,uVar1,0);
  return;
}


