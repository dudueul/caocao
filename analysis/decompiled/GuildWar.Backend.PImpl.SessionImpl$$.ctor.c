// name: GuildWar.Backend.PImpl.SessionImpl$$.ctor
// entry: 01b2de3c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl___ctor(long param_1,undefined8 param_2)

{
  long lVar1;
  long *plVar2;
  code *pcVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  
  if ((bRam00000000049fbdef & 1) == 0) {
    func_0x010c65d4(0xc033);
    bRam00000000049fbdef = 1;
  }
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0x12a) & 1) !=
       0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredString_TypeInfo + 0xd8) == 0
             )) {
    func_0x010da55c();
  }
  uVar5 = CodeStage_AntiCheat_ObscuredTypes_ObscuredString__op_Implicit(0,uVar5,0);
  *(undefined8 *)(param_1 + 0x60) = uVar5;
  *(undefined8 *)(param_1 + 0x6c) = 0x4561000045610000;
  *(undefined4 *)(param_1 + 0x98) = 0x40a00000;
  *(undefined4 *)(param_1 + 0xa0) = 0x40a00000;
  *(undefined4 *)(param_1 + 0xa8) = 0x40a00000;
  GuildWar_Backend_Session___ctor(param_1,0);
  *(undefined8 *)(param_1 + 0x20) = param_2;
  lVar1 = func_0x010fdda8(_class_UnityEngine_GameObject_TypeInfo);
  UnityEngine_GameObject___ctor(lVar1,_str_GuildWarNetworkSession,0);
  *(long *)(param_1 + 0x18) = lVar1;
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  plVar2 = (long *)UnityEngine_GameObject__get_transform(lVar1,0);
  if (((*(byte *)(_class_Singleton<Manager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<Manager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Singleton<Manager>_TypeInfo);
  }
  lVar1 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<Manager>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  lVar1 = UnityEngine_Component__get_gameObject(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar5 = UnityEngine_GameObject__get_transform(lVar1,0);
  if (plVar2 == (long *)0x0) {
    func_0x010eec64(0);
  }
  if ((bRam0000000004a04abd & 1) == 0) {
    func_0x010c65d4(0xd6f9,uVar5,0);
    bRam0000000004a04abd = 1;
  }
  if ((plVar2 != (long *)0x0) && (*plVar2 == _class_UnityEngine_RectTransform_TypeInfo)) {
    if (((*(byte *)(_class_UnityEngine_Debug_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Debug_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    UnityEngine_Debug__LogWarning
              (0,
               _str_Parent_of_RectTransform_is_being_set_with_parent_property__Consider_using_the_SetParent_method_instead__with_the_worldPositionStays_argument_set_to_false__This_will_retain_local_or
               ,plVar2,0);
  }
  pcVar3 = pcRam0000000004a04ac8;
  if ((pcRam0000000004a04ac8 == (code *)0x0) &&
     (pcVar3 = (code *)func_0x0111e49c(&UNK_039a657f), pcVar3 == (code *)0x0)) {
    uVar4 = func_0x010f000c(&UNK_039a657f);
    func_0x010eebec(uVar4,0,0);
  }
  pcRam0000000004a04ac8 = pcVar3;
                    /* WARNING: Could not recover jumptable at 0x02ad6038. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04ac8)(plVar2,uVar5,1);
  return;
}


