// name: Chat.Chatting$$SetChatRoomLocale
// entry: 01d27b2c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__SetChatRoomLocale(undefined8 param_1,undefined4 param_2)

{
  uint uVar1;
  long lVar2;
  ulong uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  code *pcVar6;
  undefined8 extraout_x1;
  undefined8 uVar7;
  
  if ((bRam00000000049fcef4 & 1) == 0) {
    func_0x010c65d4(0x25f5);
    bRam00000000049fcef4 = 1;
  }
  if (((*(byte *)(_class_Singleton<UserOptions>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<UserOptions>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar2 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<UserOptions>_get_instance__)
  ;
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = UserOptions__get_chatRoomLocaleEnabled(lVar2,0);
  if ((uVar3 & 1) == 0) {
    param_2 = 0;
  }
  if (((*(byte *)(_class_Chat_Chatting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Chat_Chatting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Chat_Chatting_TypeInfo);
  }
  uVar7 = **(undefined8 **)(_class_Chat_Chatting_TypeInfo + 0xb8);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs_TypeInfo + 0x12a) & 1) != 0
      ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs_TypeInfo + 0xd8) == 0))
  {
    func_0x010da55c();
  }
  if ((bRam00000000049ff2c6 & 1) == 0) {
    func_0x010c65d4(0x916c,uVar7,param_2,0);
    bRam00000000049ff2c6 = 1;
  }
  lVar2 = _class_CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs_TypeInfo;
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs_TypeInfo + 0x12a) & 1) != 0
      ) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs_TypeInfo + 0xd8) == 0))
  {
    lVar2 = func_0x010da55c();
  }
  uVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs__EncryptKey(lVar2,uVar7);
  uVar7 = CodeStage_AntiCheat_ObscuredTypes_ObscuredPrefs__EncryptIntValue(uVar4,uVar7,param_2);
  uVar5 = 0;
  if ((bRam0000000004a04240 & 1) == 0) {
    uVar5 = func_0x010c65d4(0xa00b,uVar4,uVar7,0);
    bRam0000000004a04240 = 1;
  }
  uVar1 = UnityEngine_PlayerPrefs__TrySetSetString(uVar5,uVar4,uVar7);
  if ((uVar1 & 1) == 0) {
    uVar4 = func_0x010fdda8(_class_UnityEngine_PlayerPrefsException_TypeInfo);
    System_Exception___ctor(uVar4,_str_Could_not_store_preference_value,0);
    uVar7 = _mm_Method_UnityEngine_PlayerPrefs_SetString__;
    func_0x010eebec(uVar4,0,_mm_Method_UnityEngine_PlayerPrefs_SetString__);
    func_0x0112148c();
    pcVar6 = pcRam0000000004a04248;
    if ((pcRam0000000004a04248 == (code *)0x0) &&
       (pcVar6 = (code *)func_0x0111e49c(&UNK_039a2890), pcVar6 == (code *)0x0)) {
      uVar4 = func_0x010f000c(&UNK_039a2890);
      func_0x010eebec(uVar4,0,0);
    }
    pcRam0000000004a04248 = pcVar6;
                    /* WARNING: Could not recover jumptable at 0x02ac2af8. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04248)(extraout_x1,uVar7);
    return;
  }
  return;
}


