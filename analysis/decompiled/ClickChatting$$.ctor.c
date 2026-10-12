// name: ClickChatting$$.ctor
// entry: 01d409d8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined1  [16] ClickChatting___ctor(ulong param_1)

{
  undefined1 auVar1 [16];
  
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


