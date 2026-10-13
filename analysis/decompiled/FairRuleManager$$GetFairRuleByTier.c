// name: FairRuleManager$$GetFairRuleByTier
// entry: 01749dbc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 FairRuleManager__GetFairRuleByTier(long param_1,undefined4 param_2)

{
  uint uVar1;
  int iVar2;
  undefined4 uVar3;
  undefined8 uVar4;
  long lVar5;
  long lVar6;
  int iVar7;
  undefined1 auVar8 [16];
  
  uVar3 = FairRuleManager__GetTierGrade();
  if ((bRam00000000049f9c14 & 1) == 0) {
    func_0x010c65d4(0x566d);
    bRam00000000049f9c14 = 1;
  }
  lVar6 = *(long *)(param_1 + 0x18);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar1 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__ContainsKey
                    (lVar6,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_ContainsKey__
                    );
  if ((uVar1 & 1) == 0) {
    lVar6 = *(long *)(param_1 + 0x18);
    uVar4 = func_0x010fdda8(_class_System_Collections_Generic_Dictionary<int,_FairRule>_TypeInfo);
    System_Collections_Generic_Dictionary<int,_WorldBossShopHistory>___ctor
              (uVar4,_mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>__ctor__);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__set_Item
              (lVar6,param_2,uVar4,
               _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_set_Item__
              );
    lVar6 = PermanentData__get_instance(0,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = PermanentData__get_gameModeRules(lVar6,0);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0x12a) & 1) != 0
        ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo + 0xd8) == 0))
    {
      func_0x010da55c(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredInt_TypeInfo);
    }
    auVar8 = CodeStage_AntiCheat_ObscuredTypes_ObscuredInt__op_Implicit(0,param_2,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = GameModeRules__get_Item(lVar6,auVar8._0_8_,auVar8._8_8_ & 0xffffffff,0);
    if (lVar6 == 0) {
      return 0;
    }
    lVar5 = GameModeRule__get_fairTreasureEnhance(lVar6,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    iVar2 = System_Collections_Generic_List<ObscuredInt>__get_Count
                      (lVar5,_mm_Method_System_Collections_Generic_List<ObscuredInt>_get_Count__);
    if (0 < iVar2) {
      iVar7 = 0;
      do {
        uVar4 = func_0x010fdda8(_class_FairRule_TypeInfo);
        iVar7 = iVar7 + 1;
        FairRule___ctor(uVar4,lVar6,iVar7);
        FairRuleManager__SetFairRule(param_1,param_2,uVar4);
      } while (iVar2 != iVar7);
    }
  }
  lVar6 = *(long *)(param_1 + 0x18);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__get_Item
                    (lVar6,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_get_Item__
                    );
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar1 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                    (lVar6,uVar3,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_ContainsKey__);
  if ((uVar1 & 1) == 0) {
    lVar6 = *(long *)(param_1 + 0x18);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__get_Item
                      (lVar6,param_2,
                       _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_get_Item__
                      );
    uVar4 = FairRuleManager__GetDefaultFairRule(param_1,uVar3);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Add
              (lVar6,uVar3,uVar4,
               _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_Add__);
  }
  lVar6 = *(long *)(param_1 + 0x18);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  lVar6 = System_Collections_Generic_Dictionary<EnumData_BattleMode,_object>__get_Item
                    (lVar6,param_2,
                     _mm_Method_System_Collections_Generic_Dictionary<EnumData_BattleMode,_Dictionary<int,_FairRule>>_get_Item__
                    );
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = System_Collections_Generic_Dictionary<int,_object>__get_Item
                    (lVar6,uVar3,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_FairRule>_get_Item__);
  return uVar4;
}


