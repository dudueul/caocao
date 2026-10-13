// name: nAI.Score$$GetDamageMultiTargetPoint
// entry: 022c25a4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

float nAI_Score__GetDamageMultiTargetPoint(undefined8 param_1,long param_2)

{
  int iVar1;
  int iVar2;
  undefined4 uVar3;
  long lVar4;
  long *plVar5;
  
  if ((bRam00000000049fff90 & 1) == 0) {
    func_0x010c65d4(0xbdb3);
    bRam00000000049fff90 = 1;
  }
  lVar4 = _class_nAI_Score_TypeInfo;
  if (((*(byte *)(_class_nAI_Score_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_nAI_Score_TypeInfo + 0xd8) == 0)) {
    lVar4 = func_0x010da55c();
  }
  iVar1 = nAI_Score__GetAtkEffectedEnemyCount(lVar4,param_2);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  plVar5 = *(long **)(param_2 + 0x28);
  if (plVar5 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar4 = (**(code **)(*plVar5 + 0x340))(plVar5,*(undefined8 *)(*plVar5 + 0x348));
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
  plVar5 = *(long **)(param_2 + 0x28);
  if (plVar5 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar4 = (**(code **)(*plVar5 + 0x340))(plVar5,*(undefined8 *)(*plVar5 + 0x348));
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


