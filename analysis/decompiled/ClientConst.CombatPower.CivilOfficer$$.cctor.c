// name: ClientConst.CombatPower.CivilOfficer$$.cctor
// entry: 0210aab4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void ClientConst_CombatPower_CivilOfficer___cctor(void)

{
  long lVar1;
  long lVar2;
  undefined8 uVar3;
  undefined1 auVar4 [12];
  
  if ((bRam00000000049ff1b7 & 1) == 0) {
    func_0x010c65d4(0x265b);
    bRam00000000049ff1b7 = 1;
  }
  lVar1 = func_0x01101ad4(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt___TypeInfo,5);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  lVar2 = _class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo;
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0)) {
    lVar2 = func_0x010da55c();
  }
  auVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(lVar2,10);
  uVar3 = auVar4._0_8_;
  if (*(int *)(lVar1 + 0x18) == 0) {
    uVar3 = func_0x010ef850();
    uVar3 = func_0x010eebec(uVar3,0,0);
  }
  *(undefined1 (*) [12])(lVar1 + 0x20) = auVar4;
  auVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(uVar3,0x4b);
  uVar3 = auVar4._0_8_;
  if (*(uint *)(lVar1 + 0x18) < 2) {
    uVar3 = func_0x010ef850();
    uVar3 = func_0x010eebec(uVar3,0,0);
  }
  *(undefined1 (*) [12])(lVar1 + 0x2c) = auVar4;
  auVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(uVar3,0x87);
  uVar3 = auVar4._0_8_;
  if (*(uint *)(lVar1 + 0x18) < 3) {
    uVar3 = func_0x010ef850();
    uVar3 = func_0x010eebec(uVar3,0,0);
  }
  *(undefined1 (*) [12])(lVar1 + 0x38) = auVar4;
  auVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(uVar3,0x32);
  uVar3 = auVar4._0_8_;
  if (*(uint *)(lVar1 + 0x18) < 4) {
    uVar3 = func_0x010ef850();
    uVar3 = func_0x010eebec(uVar3,0,0);
  }
  *(undefined1 (*) [12])(lVar1 + 0x44) = auVar4;
  auVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(uVar3,100);
  if (*(uint *)(lVar1 + 0x18) < 5) {
    uVar3 = func_0x010ef850();
    func_0x010eebec(uVar3,0,0);
  }
  *(undefined1 (*) [12])(lVar1 + 0x50) = auVar4;
  **(long **)(_class_ClientConst_CombatPower_CivilOfficer_TypeInfo + 0xb8) = lVar1;
  return;
}


