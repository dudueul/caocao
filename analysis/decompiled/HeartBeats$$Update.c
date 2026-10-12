// name: HeartBeats$$Update
// entry: 016cb1f0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__Update(long param_1)

{
  ulong uVar1;
  undefined4 uVar2;
  
  if ((bRam00000000049f97a5 & 1) == 0) {
    func_0x010c65d4(0x6a27);
    bRam00000000049f97a5 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Input_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Input_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = UnityEngine_Input__get_anyKeyDown(0,0);
  if ((uVar1 & 1) != 0) {
    uVar2 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
    *(undefined4 *)(param_1 + 0x20) = uVar2;
  }
  return;
}


