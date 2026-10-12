// name: HeartBeats$$.ctor
// entry: 016cb080

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined1  [16] HeartBeats___ctor(ulong param_1)

{
  undefined1 auVar1 [16];
  
  if ((bRam00000000049f97a3 & 1) == 0) {
    func_0x010c65d4(0x6a28);
    bRam00000000049f97a3 = 1;
  }
  *(undefined8 *)(param_1 + 0x18) = 0x4b00000003c;
  if (((*(byte *)(_class_Singleton<HeartBeats>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HeartBeats>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if (param_1 == 0) {
    func_0x010eec64(0,_mm_Method_Singleton<HeartBeats>__ctor__);
  }
  if ((bRam0000000004a05620 & 1) == 0) {
    func_0x010c65d4(0x2e83,0);
    bRam0000000004a05620 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  auVar1._8_8_ = 0;
  auVar1._0_8_ = param_1;
  return auVar1;
}


