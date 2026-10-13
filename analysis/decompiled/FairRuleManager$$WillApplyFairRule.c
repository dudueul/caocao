// name: FairRuleManager$$WillApplyFairRule
// entry: 0174a248

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool FairRuleManager__WillApplyFairRule(undefined8 param_1,uint param_2,long param_3)

{
  int iVar1;
  uint uVar2;
  int iVar3;
  long lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  uint uStack_34;
  
  if ((bRam00000000049f9c1b & 1) == 0) {
    func_0x010c65d4(0x567f);
    bRam00000000049f9c1b = 1;
  }
  uVar2 = FairRuleManager__WillApplyFairRule(param_1);
  if (param_3 == 0) {
    return false;
  }
  if (((uVar2 ^ 1) & 1) != 0) {
    return false;
  }
  lVar4 = FairRuleManager__GetFairRule(param_1);
  if (lVar4 != 0) {
    iVar1 = param_2 + 6;
    if (4 < param_2) {
      iVar1 = 0;
    }
    if (iVar1 == 8) {
      return *(char *)(lVar4 + 0x1c) == '\0';
    }
    if (iVar1 == 10) {
      iVar1 = *(int *)(lVar4 + 0x20);
      iVar3 = RuneItemSlot__get_dbLevel(param_3,0);
      return iVar1 < iVar3;
    }
    if (iVar1 != 0) {
      return false;
    }
    uStack_34 = param_2;
    uVar5 = func_0x010fdd08(_class_EnumData_FairRuleType_TypeInfo,&uStack_34);
    uVar6 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
    System_ArgumentOutOfRangeException___ctor(uVar6,_str_ruleType,uVar5,0,0);
    func_0x010eebec(uVar6,0,_mm_Method_FairRuleManager_WillApplyFairRule__);
    func_0x0112148c();
  }
  return false;
}


