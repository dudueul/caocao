// name: SpAction434$$_GetDamageIncreasePct
// entry: 0230435c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

int SpAction434___GetDamageIncreasePct
              (undefined8 param_1,undefined4 param_2,int param_3,int param_4)

{
  undefined4 uVar1;
  int iVar2;
  
  if ((bRam0000000004a001ef & 1) == 0) {
    func_0x010c65d4(0xc87a);
    bRam0000000004a001ef = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = UnityEngine_Mathf__CeilToInt
                    (((float)(param_3 - param_4) / (float)param_4) * 0.5 * 100.0,0,0);
  iVar2 = UnityEngine_Mathf__Min(0,param_2,uVar1,0);
  if (iVar2 < 0) {
    iVar2 = 0;
  }
  return iVar2;
}


