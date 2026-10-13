// name: BattlePlay.BattlePlayManager$$FinishWatchLoading
// entry: 0197a340

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__FinishWatchLoading(undefined8 param_1)

{
  undefined1 *puVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  code *pcVar5;
  undefined8 uVar6;
  undefined8 unaff_x19;
  undefined8 unaff_x20;
  undefined8 unaff_x21;
  undefined8 unaff_x22;
  undefined1 *unaff_x29;
  code *unaff_x30;
  undefined1 auVar7 [16];
  
  lVar3 = BattlePlay_BattlePlayManager__FinishLoadingAndLeaveWatch();
  puVar1 = &stack0xfffffffffffffff0;
  uVar4 = param_1;
  if ((bRam0000000004a0404a & 1) == 0) {
    uVar4 = func_0x010c65d4(0x8979,lVar3,0);
    bRam0000000004a0404a = 1;
  }
  if (lVar3 == 0) {
    unaff_x19 = func_0x010fdda8(_class_System_NullReferenceException_TypeInfo);
    System_NullReferenceException___ctor(unaff_x19,_str_routine_is_null,0);
  }
  else {
    uVar2 = UnityEngine_MonoBehaviour__IsObjectMonoBehaviour(uVar4,param_1);
    uVar4 = param_1;
    if ((uVar2 & 1) != 0) goto code_r0x02abd390;
    unaff_x19 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
    System_ArgumentException___ctor
              (unaff_x19,_str_Coroutines_can_only_be_stopped_on_a_MonoBehaviour,0);
  }
  func_0x010eebec(unaff_x19,0,_mm_Method_UnityEngine_MonoBehaviour_StartCoroutine__);
  unaff_x30 = UnityEngine_MonoBehaviour__StartCoroutineManaged2;
  auVar7 = func_0x0112148c();
  lVar3 = auVar7._8_8_;
  register0x00000008 = (BADSPACEBASE *)&stack0xffffffffffffffd0;
  uVar4 = auVar7._0_8_;
  unaff_x20 = param_1;
  unaff_x21 = 0x4a04000;
  unaff_x29 = puVar1;
code_r0x02abd390:
  *(undefined8 *)((long)register0x00000008 + -0x30) = unaff_x22;
  *(undefined8 *)((long)register0x00000008 + -0x28) = unaff_x21;
  *(undefined8 *)((long)register0x00000008 + -0x20) = unaff_x20;
  *(undefined8 *)((long)register0x00000008 + -0x18) = unaff_x19;
  *(undefined1 **)((long)register0x00000008 + -0x10) = unaff_x29;
  *(code **)((long)register0x00000008 + -8) = unaff_x30;
  pcVar5 = pcRam0000000004a040b0;
  if ((pcRam0000000004a040b0 == (code *)0x0) &&
     (pcVar5 = (code *)func_0x0111e49c(&UNK_039a1f22), pcVar5 == (code *)0x0)) {
    *(undefined8 *)((long)register0x00000008 + -0x38) = 0;
    uVar6 = func_0x010f000c(&UNK_039a1f22);
    func_0x010eebec(uVar6,0,0);
    pcVar5 = *(code **)((long)register0x00000008 + -0x38);
  }
  pcRam0000000004a040b0 = pcVar5;
                    /* WARNING: Could not recover jumptable at 0x02abd408. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a040b0)(uVar4,lVar3);
  return;
}


