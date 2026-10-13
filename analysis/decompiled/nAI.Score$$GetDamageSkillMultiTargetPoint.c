// name: nAI.Score$$GetDamageSkillMultiTargetPoint
// entry: 022c2864

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

float nAI_Score__GetDamageSkillMultiTargetPoint(undefined8 param_1,long param_2)

{
  int iVar1;
  int iVar2;
  undefined4 uVar3;
  long lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  long *plVar7;
  undefined8 *puVar8;
  
  if ((bRam00000000049fff91 & 1) == 0) {
    func_0x010c65d4(0xbdb4);
    bRam00000000049fff91 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
    uVar5 = uRam0000000000000028;
    puVar8 = (undefined8 *)0x28;
    func_0x010eec64(0);
    uVar6 = uRam0000000000000030;
    func_0x010eec64(0);
  }
  else {
    puVar8 = (undefined8 *)(param_2 + 0x28);
    uVar5 = *puVar8;
    uVar6 = *(undefined8 *)(param_2 + 0x30);
  }
  uVar3 = *(undefined4 *)(param_2 + 0x20);
  if (((*(byte *)(_class_UnitCommonFunc_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnitCommonFunc_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = UnitCommonFunc__GetSkillTargets(0,uVar5,uVar6,uVar3,1,0);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                    (lVar4,_mm_Method_System_Collections_Generic_List<UnitData>_get_Count__);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  plVar7 = (long *)*puVar8;
  if (plVar7 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar4 = (**(code **)(*plVar7 + 0x340))(plVar7,*(undefined8 *)(*plVar7 + 0x348));
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar4 = *(long *)(lVar4 + 0x78);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  iVar2 = AITendency__get_cMultiAtk(lVar4,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  plVar7 = (long *)*puVar8;
  if (plVar7 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar4 = (**(code **)(*plVar7 + 0x340))(plVar7,*(undefined8 *)(*plVar7 + 0x348));
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar4 = *(long *)(lVar4 + 0x78);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = AITendency__get_cMaxMulti(lVar4,0);
  if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_UnityEngine_Mathf_TypeInfo);
  }
  iVar1 = UnityEngine_Mathf__Min(0,uVar3,iVar1 + -1,0);
  return (float)iVar2 * 0.01 * (float)iVar1 + 1.0;
}


