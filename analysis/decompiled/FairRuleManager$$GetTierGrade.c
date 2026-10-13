// name: FairRuleManager$$GetTierGrade
// entry: 01749664

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong FairRuleManager__GetTierGrade(undefined8 param_1,uint param_2)

{
  undefined4 uVar1;
  int iVar2;
  undefined8 uVar3;
  long lVar4;
  ulong uVar5;
  
  if ((bRam00000000049f9c16 & 1) == 0) {
    func_0x010c65d4(0x5672);
    bRam00000000049f9c16 = 1;
  }
  if (param_2 < 0x16) {
    iVar2 = *(int *)(&UNK_03993fc0 + (long)(int)param_2 * 4);
    if (iVar2 == 9) goto code_r0x017496f8;
    if (iVar2 == 0x11) {
      lVar4 = CompetitionManager__get_instance(0,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      return (ulong)*(uint *)(lVar4 + 0x54);
    }
    if (iVar2 != 0) {
      return 1;
    }
  }
  uVar3 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
  System_ArgumentOutOfRangeException___ctor(uVar3,0);
  func_0x010eebec(uVar3,0,_mm_Method_FairRuleManager_GetTierGrade__);
  func_0x0112148c();
code_r0x017496f8:
  lVar4 = MyUserData__get_instance(0,0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar4 = UserData__get_pvpStats(lVar4,0);
  if (lVar4 != 0) {
    lVar4 = MyUserData__get_instance(0,0);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    lVar4 = UserData__get_pvpStats(lVar4,0);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    lVar4 = UserPvpStats__GetTier(lVar4,0);
    if (lVar4 != 0) {
      lVar4 = MyUserData__get_instance(0,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      lVar4 = UserData__get_pvpStats(lVar4,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      lVar4 = UserPvpStats__GetTier(lVar4,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam00000000049fb3b1 & 1) == 0) {
        func_0x010c65d4(0xcfe5,0);
        bRam00000000049fb3b1 = 1;
      }
      uVar3 = *(undefined8 *)(lVar4 + 0x38);
      uVar1 = *(undefined4 *)(lVar4 + 0x40);
      if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) !=
           0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) ==
                  0)) {
        func_0x010da55c();
      }
      uVar5 = func_0x021275d4(&stack0xffffffffffffffe0,uVar3,uVar1,0);
      return uVar5;
    }
  }
  return 1;
}


