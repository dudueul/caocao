// name: GuildWar.Backend.PImpl.SessionImpl$$get_maintenance
// entry: 01b30c30

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

uint GuildWar_Backend_PImpl_SessionImpl__get_maintenance(long param_1)

{
  undefined4 uVar1;
  uint uVar2;
  undefined8 uVar3;
  
  if ((bRam00000000049fbdf2 & 1) == 0) {
    func_0x010c65d4(0xc03d);
    bRam00000000049fbdf2 = 1;
  }
  uVar3 = *(undefined8 *)(param_1 + 0x8c);
  uVar1 = *(undefined4 *)(param_1 + 0x94);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = func_0x02122774(&stack0xffffffffffffffe0,uVar3,uVar1,0);
  return uVar2 & 1;
}


