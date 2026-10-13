// name: Multiplay.MultiplayManager$$OnWatchFinishedPostActionOnLoading
// entry: 013ab728

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnWatchFinishedPostActionOnLoading(long param_1)

{
  undefined1 *puVar1;
  uint uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  long lVar6;
  code *pcVar7;
  undefined8 unaff_x19;
  long unaff_x20;
  undefined8 unaff_x21;
  undefined8 uVar8;
  undefined8 unaff_x22;
  undefined1 *unaff_x29;
  code *unaff_x30;
  undefined1 auVar9 [16];
  
  if ((bRam00000000049f7b1e & 1) == 0) {
    func_0x010c65d4(0x8a71);
    bRam00000000049f7b1e = 1;
  }
  Multiplay_MultiplayManager__SetState(param_1,0);
  uVar5 = _mm_Method_Multiplay_MultiplayManager_<OnWatchFinishedPostActionOnLoading>m__1__;
  uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar3,param_1,uVar5,0);
  if ((bRam00000000049f7b43 & 1) == 0) {
    func_0x010c65d4(0x8a9e);
    bRam00000000049f7b43 = 1;
  }
  if ((*(long *)(param_1 + 0x48) == 0) ||
     (lVar4 = BattlePlay_BattlePlayData__GetWinner(*(long *)(param_1 + 0x48),0), lVar4 == 0)) {
    uVar5 = ___L(0,_str_경쟁전_로비로_이동합니다_,0);
  }
  else {
    uVar5 = ___L(0,_str__0__국이_승리하였습니다_,0);
    if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Localization_TypeInfo);
    }
    uVar5 = Localization__Get(0,uVar5,0);
    uVar8 = *(undefined8 *)(lVar4 + 0x18);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar5 = System_String__Format(0,uVar5,uVar8,0);
  }
  uVar8 = ___L(0,_str_관전_종료,0);
  lVar6 = Multiplay_MultiplayManager__GoToLobbyWithDelay(param_1,uVar8,uVar5,uVar3);
  puVar1 = &stack0xfffffffffffffff0;
  lVar4 = param_1;
  if ((bRam0000000004a0404a & 1) == 0) {
    lVar4 = func_0x010c65d4(0x8979,lVar6,0);
    bRam0000000004a0404a = 1;
  }
  if (lVar6 == 0) {
    unaff_x19 = func_0x010fdda8(_class_System_NullReferenceException_TypeInfo);
    System_NullReferenceException___ctor(unaff_x19,_str_routine_is_null,0);
  }
  else {
    uVar2 = UnityEngine_MonoBehaviour__IsObjectMonoBehaviour(lVar4,param_1);
    lVar4 = param_1;
    if ((uVar2 & 1) != 0) goto code_r0x02abd390;
    unaff_x19 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
    System_ArgumentException___ctor
              (unaff_x19,_str_Coroutines_can_only_be_stopped_on_a_MonoBehaviour,0);
  }
  func_0x010eebec(unaff_x19,0,_mm_Method_UnityEngine_MonoBehaviour_StartCoroutine__);
  unaff_x30 = UnityEngine_MonoBehaviour__StartCoroutineManaged2;
  auVar9 = func_0x0112148c();
  lVar6 = auVar9._8_8_;
  register0x00000008 = (BADSPACEBASE *)&stack0xffffffffffffffd0;
  lVar4 = auVar9._0_8_;
  unaff_x20 = param_1;
  unaff_x21 = 0x4a04000;
  unaff_x29 = puVar1;
code_r0x02abd390:
  *(undefined8 *)((long)register0x00000008 + -0x30) = unaff_x22;
  *(undefined8 *)((long)register0x00000008 + -0x28) = unaff_x21;
  *(long *)((long)register0x00000008 + -0x20) = unaff_x20;
  *(undefined8 *)((long)register0x00000008 + -0x18) = unaff_x19;
  *(undefined1 **)((long)register0x00000008 + -0x10) = unaff_x29;
  *(code **)((long)register0x00000008 + -8) = unaff_x30;
  pcVar7 = pcRam0000000004a040b0;
  if ((pcRam0000000004a040b0 == (code *)0x0) &&
     (pcVar7 = (code *)func_0x0111e49c(&UNK_039a1f22), pcVar7 == (code *)0x0)) {
    *(undefined8 *)((long)register0x00000008 + -0x38) = 0;
    uVar5 = func_0x010f000c(&UNK_039a1f22);
    func_0x010eebec(uVar5,0,0);
    pcVar7 = *(code **)((long)register0x00000008 + -0x38);
  }
  pcRam0000000004a040b0 = pcVar7;
                    /* WARNING: Could not recover jumptable at 0x02abd408. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a040b0)(lVar4,lVar6);
  return;
}


