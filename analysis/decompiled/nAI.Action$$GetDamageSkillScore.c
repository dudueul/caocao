// name: nAI.Action$$GetDamageSkillScore
// entry: 022a897c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

float nAI_Action__GetDamageSkillScore(long *param_1)

{
  int iVar1;
  undefined4 uVar2;
  long lVar3;
  undefined8 uVar4;
  int iVar5;
  long *plVar6;
  long lVar7;
  float extraout_s0;
  float extraout_s0_00;
  float extraout_s0_01;
  float extraout_s0_02;
  float extraout_s0_03;
  float extraout_s0_04;
  float fVar8;
  float fVar9;
  float fVar10;
  float fVar11;
  float fVar12;
  float fVar13;
  float fVar14;
  float fVar15;
  float fVar16;
  float fVar17;
  float fVar18;
  undefined1 auStack_140 [88];
  undefined1 auStack_e8 [88];
  
  if ((bRam00000000049fff28 & 1) == 0) {
    func_0x010c65d4(0x317);
    bRam00000000049fff28 = 1;
  }
  func_0x01007a60(auStack_e8,0,0x58);
  lVar3 = _class_nAI_Score_TypeInfo;
  if (((*(byte *)(_class_nAI_Score_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_nAI_Score_TypeInfo + 0xd8) == 0)) {
    lVar3 = func_0x010da55c();
  }
  uVar4 = nAI_Score__GetSkillDamagePoint(lVar3,param_1);
  uVar4 = nAI_Score__GetMPUsePoint(uVar4,param_1);
  uVar4 = nAI_Score__GetSkillPoint(uVar4,param_1);
  uVar4 = nAI_Score__GetActorSpActionPoint(uVar4,param_1);
  uVar4 = nAI_Score__GetTargetSpActionPoint(uVar4,param_1);
  uVar4 = nAI_Score__GetTargetValuePoint(uVar4,param_1);
  fVar8 = (float)nAI_Score__GetTargetAdditionalScore(uVar4,param_1);
  iVar5 = 0;
  while( true ) {
    lVar3 = param_1[7];
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                      (lVar3,_mm_Method_System_Collections_Generic_List<TileNode>_get_Count__);
    if (iVar1 <= iVar5) break;
    plVar6 = (long *)param_1[5];
    if (plVar6 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar3 = (**(code **)(*plVar6 + 0x340))(plVar6,*(undefined8 *)(*plVar6 + 0x348));
    lVar7 = param_1[7];
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = System_Collections_Generic_List<object>__get_Item
                      (lVar7,iVar5,_mm_Method_System_Collections_Generic_List<TileNode>_get_Item__);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = *(undefined8 *)(lVar7 + 0x24);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    nAI_Ai__GetTileScore(auStack_140,lVar3,uVar4);
    func_0x01006e80(auStack_e8,auStack_140,0x58);
    fVar9 = (float)func_0x02413c08(auStack_e8,0);
    fVar10 = (float)func_0x02413858(auStack_e8,0);
    fVar11 = (float)func_0x0241376c(auStack_e8,0);
    fVar12 = (float)func_0x02413b1c(auStack_e8,0);
    fVar13 = (float)func_0x02413944(auStack_e8,0);
    fVar14 = (float)func_0x02413a30(auStack_e8,0);
    fVar15 = (float)func_0x02413cf4(auStack_e8,0);
    lVar3 = param_1[7];
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = System_Collections_Generic_List<object>__get_Item
                      (lVar3,iVar5,_mm_Method_System_Collections_Generic_List<TileNode>_get_Item__);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = *(undefined8 *)(lVar3 + 0x24);
    lVar3 = _class_nAI_Score_TypeInfo;
    if (((*(byte *)(_class_nAI_Score_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_nAI_Score_TypeInfo + 0xd8) == 0)) {
      lVar3 = func_0x010da55c();
    }
    fVar16 = (float)nAI_Score__GetAiActionGoalPoint(lVar3,param_1,uVar4);
    fVar17 = (float)func_0x02413de0(auStack_e8,0);
    fVar18 = (float)func_0x02413640(auStack_e8,0);
    lVar3 = param_1[7];
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    lVar3 = System_Collections_Generic_List<object>__get_Item
                      (lVar3,iVar5,_mm_Method_System_Collections_Generic_List<TileNode>_get_Item__);
    uVar2 = (**(code **)(*param_1 + 400))(param_1,*(undefined8 *)(*param_1 + 0x198));
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    TileNode__set_Item(fVar9 + 0.0 + fVar10 + fVar11 + fVar12 + fVar13 + fVar14 + fVar15 + fVar16 +
                       fVar17 + fVar18,lVar3,uVar2,0);
    iVar5 = iVar5 + 1;
  }
  return extraout_s0 + 0.0 + extraout_s0_00 + extraout_s0_01 + extraout_s0_02 + extraout_s0_03 +
         extraout_s0_04 + fVar8;
}


