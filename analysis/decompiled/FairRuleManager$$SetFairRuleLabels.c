// name: FairRuleManager$$SetFairRuleLabels
// entry: 0174a5a0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FairRuleManager__SetFairRuleLabels(undefined8 param_1,long param_2,long param_3)

{
  undefined8 uVar1;
  long *plVar2;
  long lVar3;
  long lVar4;
  undefined8 uVar5;
  int iVar6;
  long lVar7;
  uint uVar8;
  ulong uVar9;
  undefined4 uStack_5c;
  undefined4 uStack_58;
  undefined4 uStack_54;
  
  if ((bRam00000000049f9c1f & 1) == 0) {
    func_0x010c65d4(0x5679);
    bRam00000000049f9c1f = 1;
  }
  if (param_3 == 0) {
    return;
  }
  if (*(int *)(param_3 + 0x18) == 0) {
    return;
  }
  if (param_2 == 0) {
    lVar3 = *(long *)(param_3 + 0x20);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = UnityEngine_Component__get_gameObject(lVar3,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    UnityEngine_GameObject__SetActive(lVar3,1,0);
    if (*(int *)(param_3 + 0x18) == 0) {
      uVar1 = func_0x010ef850();
      func_0x010eebec(uVar1,0,0);
    }
    lVar3 = *(long *)(param_3 + 0x20);
    uVar1 = ___L(0,_str_제한_없음,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    UILabelLocalized__SetKey(lVar3,uVar1,0);
    iVar6 = (int)*(undefined8 *)(param_3 + 0x18);
    if (iVar6 < 2) {
      return;
    }
    lVar3 = 0;
    do {
      if ((ulong)*(uint *)(param_3 + 0x18) <= lVar3 + 1U) {
        uVar1 = func_0x010ef850();
        func_0x010eebec(uVar1,0,0);
      }
      lVar7 = *(long *)(param_3 + 0x28 + lVar3 * 8);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = UnityEngine_Component__get_gameObject(lVar7,0);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      UnityEngine_GameObject__SetActive(lVar7,0,0);
      lVar3 = lVar3 + 1;
    } while (iVar6 + -1 != (int)lVar3);
    return;
  }
  iVar6 = *(int *)(param_2 + 0x14);
  if (((*(byte *)(_class_FairRuleManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_FairRuleManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if (iVar6 < **(int **)(_class_FairRuleManager_TypeInfo + 0xb8)) {
    if (*(int *)(param_3 + 0x18) == 0) {
      uVar1 = func_0x010ef850();
      func_0x010eebec(uVar1,0,0);
    }
    lVar7 = *(long *)(param_3 + 0x20);
    uVar1 = ___L(0,_str_보물_강화_제한_____0_,0);
    plVar2 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
    uStack_54 = *(undefined4 *)(param_2 + 0x14);
    lVar3 = func_0x010fdd08(_class_int_TypeInfo,&uStack_54);
    if (plVar2 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar3 != 0) && (lVar4 = func_0x010fdfcc(lVar3,*(undefined8 *)(*plVar2 + 0x40)), lVar4 == 0)
       ) {
      uVar5 = func_0x010efe90();
      func_0x010eebec(uVar5,0,0);
    }
    if ((int)plVar2[3] == 0) {
      uVar5 = func_0x010ef850();
      func_0x010eebec(uVar5,0,0);
    }
    plVar2[4] = lVar3;
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    UILabelLocalized__SetContext(lVar7,uVar1,plVar2,0);
    uVar9 = 1;
  }
  else {
    uVar9 = 0;
  }
  iVar6 = *(int *)(param_2 + 0x18);
  if (((*(byte *)(_class_FairRuleManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_FairRuleManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if (iVar6 < *(int *)(*(long *)(_class_FairRuleManager_TypeInfo + 0xb8) + 4)) {
    uVar8 = (uint)uVar9;
    if ((int)uVar8 < (int)*(uint *)(param_3 + 0x18)) {
      if (*(uint *)(param_3 + 0x18) <= uVar8) {
        uVar1 = func_0x010ef850();
        func_0x010eebec(uVar1,0,0);
      }
      lVar7 = *(long *)(param_3 + uVar9 * 8 + 0x20);
      uVar1 = ___L(0,_str_보물_강화_효과_제한_____0_,0);
      plVar2 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
      uStack_58 = *(undefined4 *)(param_2 + 0x18);
      lVar3 = func_0x010fdd08(_class_int_TypeInfo,&uStack_58);
      if (plVar2 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((lVar3 != 0) &&
         (lVar4 = func_0x010fdfcc(lVar3,*(undefined8 *)(*plVar2 + 0x40)), lVar4 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if ((int)plVar2[3] == 0) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar2[4] = lVar3;
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      UILabelLocalized__SetContext(lVar7,uVar1,plVar2,0);
      uVar9 = (ulong)(uVar8 + 1);
    }
  }
  uVar8 = (uint)uVar9;
  if ((*(ushort *)(param_2 + 0x1c) & 0xff) == 0) {
    if ((int)*(uint *)(param_3 + 0x18) <= (int)uVar8) goto code_r0x0174ac10;
    if (*(uint *)(param_3 + 0x18) <= uVar8) {
      uVar1 = func_0x010ef850();
      func_0x010eebec(uVar1,0,0);
    }
    lVar3 = *(long *)(param_3 + (long)(int)uVar8 * 8 + 0x20);
    uVar1 = ___L(0,_str_보패_미적용,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    UILabelLocalized__SetKey(lVar3,uVar1,0);
  }
  else {
    if ((*(ushort *)(param_2 + 0x1c) < 0x100) && ((int)uVar8 < (int)*(uint *)(param_3 + 0x18))) {
      if (*(uint *)(param_3 + 0x18) <= uVar8) {
        uVar1 = func_0x010ef850();
        func_0x010eebec(uVar1,0,0);
      }
      lVar3 = *(long *)(param_3 + (long)(int)uVar8 * 8 + 0x20);
      uVar1 = ___L(0,_str_보패_조합_효과_미적용,0);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      UILabelLocalized__SetKey(lVar3,uVar1,0);
      uVar8 = uVar8 + 1;
    }
    iVar6 = *(int *)(param_2 + 0x20);
    if ((iVar6 < 2) && ((int)uVar8 < (int)*(uint *)(param_3 + 0x18))) {
      if (*(uint *)(param_3 + 0x18) <= uVar8) {
        uVar1 = func_0x010ef850();
        func_0x010eebec(uVar1,0,0);
      }
      lVar7 = *(long *)(param_3 + (long)(int)uVar8 * 8 + 0x20);
      uVar1 = ___L(0,_str_보패_강화_미적용,0);
      plVar2 = (long *)func_0x01101ad4(_class_object___TypeInfo,0);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
    }
    else {
      if (((*(byte *)(_class_FairRuleManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_FairRuleManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      if ((*(int *)(*(long *)(_class_FairRuleManager_TypeInfo + 0xb8) + 0xc) <= iVar6) ||
         ((int)*(uint *)(param_3 + 0x18) <= (int)uVar8)) goto code_r0x0174ac10;
      if (*(uint *)(param_3 + 0x18) <= uVar8) {
        uVar1 = func_0x010ef850();
        func_0x010eebec(uVar1,0,0);
      }
      lVar7 = *(long *)(param_3 + (long)(int)uVar8 * 8 + 0x20);
      uVar1 = ___L(0,_str_보패_강화_제한,0);
      plVar2 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
      uStack_5c = *(undefined4 *)(param_2 + 0x20);
      lVar3 = func_0x010fdd08(_class_int_TypeInfo,&uStack_5c);
      if (plVar2 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((lVar3 != 0) &&
         (lVar4 = func_0x010fdfcc(lVar3,*(undefined8 *)(*plVar2 + 0x40)), lVar4 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if ((int)plVar2[3] == 0) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar2[4] = lVar3;
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
    }
    UILabelLocalized__SetContext(lVar7,uVar1,plVar2,0);
  }
  uVar8 = uVar8 + 1;
code_r0x0174ac10:
  if (uVar8 == 0) {
    if (*(int *)(param_3 + 0x18) == 0) {
      uVar1 = func_0x010ef850();
      func_0x010eebec(uVar1,0,0);
    }
    lVar3 = *(long *)(param_3 + 0x20);
    uVar1 = ___L(0,_str_제한_없음,0);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    UILabelLocalized__SetKey(lVar3,uVar1,0);
    uVar8 = 1;
  }
  iVar6 = (int)*(undefined8 *)(param_3 + 0x18);
  if (0 < iVar6) {
    uVar9 = 0;
    do {
      if (*(uint *)(param_3 + 0x18) <= uVar9) {
        uVar1 = func_0x010ef850();
        func_0x010eebec(uVar1,0,0);
      }
      lVar3 = *(long *)(param_3 + 0x20 + uVar9 * 8);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      lVar3 = UnityEngine_Component__get_gameObject(lVar3,0);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      UnityEngine_GameObject__SetActive(lVar3,(long)uVar9 < (long)(int)uVar8,0);
      uVar9 = uVar9 + 1;
    } while (iVar6 != (int)uVar9);
  }
  return;
}


