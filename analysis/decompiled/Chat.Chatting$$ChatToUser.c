// name: Chat.Chatting$$ChatToUser
// entry: 01d2c92c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long Chat_Chatting__ChatToUser
               (long param_1,undefined8 param_2,undefined8 param_3,undefined4 param_4)

{
  undefined1 *puVar1;
  int iVar2;
  uint uVar3;
  long lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  code *pcVar7;
  undefined8 extraout_x1;
  undefined4 uVar8;
  long lVar9;
  undefined8 unaff_x19;
  long unaff_x20;
  undefined8 unaff_x21;
  undefined8 unaff_x22;
  long lVar10;
  undefined8 uVar11;
  undefined8 uVar12;
  undefined1 *unaff_x29;
  code *unaff_x30;
  undefined1 auVar13 [16];
  
  puVar1 = &stack0xfffffffffffffff0;
  lVar4 = param_1;
  uVar5 = param_3;
  if ((bRam00000000049fcf1e & 1) == 0) {
    lVar4 = func_0x010c65d4(0x25b3);
    bRam00000000049fcf1e = 1;
  }
  uVar8 = (undefined4)uVar5;
  if (*(char *)(param_1 + 0x80) == '\0') {
    return lVar4;
  }
  iVar2 = *(int *)(param_1 + 0x84);
  lVar10 = *(long *)(param_1 + 0x48);
  *(int *)(param_1 + 0x84) = iVar2 + 1;
  lVar9 = lVar10;
  if (lVar10 == 0) {
    lVar4 = func_0x010eec64(0);
    lVar9 = *(long *)(param_1 + 0x48);
    if (lVar9 == 0) {
      uVar5 = func_0x010eec64(0);
      if ((bRam00000000049fcf1f & 1) == 0) {
        func_0x010c65d4(0x25fd);
        bRam00000000049fcf1f = 1;
      }
      lVar4 = func_0x010fdda8(_class_Chat_Chatting_<WhisperResponseTimeout>c__Iterator0_TypeInfo);
      System_Object___ctor(lVar4,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
        uRam0000000000000010 = uVar8;
        func_0x010eec64(0);
      }
      else {
        *(undefined4 *)(lVar4 + 0x10) = uVar8;
      }
      *(undefined8 *)(lVar4 + 0x18) = uVar5;
      return lVar4;
    }
  }
  uVar11 = *(undefined8 *)(lVar10 + 0x10);
  uVar12 = *(undefined8 *)(lVar9 + 0x18);
  uVar5 = Chat_Chatting__GetSubRoomId(lVar4,param_2);
  uVar6 = func_0x010fdda8(_class_Chat_ChattingMessage_TypeInfo);
  Chat_ChattingMessage___ctor(uVar6,param_3,uVar11,uVar12,uVar5,iVar2,1,param_4);
  Chat_Chatting__EmitChattingMessage(param_1,uVar6);
  lVar4 = *(long *)(param_1 + 0x90);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_List<int>__Add
            (lVar4,iVar2,_mm_Method_System_Collections_Generic_List<int>_Add__);
  lVar9 = Chat_Chatting__WhisperResponseTimeout(param_1,extraout_x1,iVar2);
  lVar4 = param_1;
  if ((bRam0000000004a0404a & 1) == 0) {
    lVar4 = func_0x010c65d4(0x8979,lVar9,0);
    bRam0000000004a0404a = 1;
  }
  if (lVar9 == 0) {
    unaff_x19 = func_0x010fdda8(_class_System_NullReferenceException_TypeInfo);
    System_NullReferenceException___ctor(unaff_x19,_str_routine_is_null,0);
  }
  else {
    uVar3 = UnityEngine_MonoBehaviour__IsObjectMonoBehaviour(lVar4,param_1);
    lVar4 = param_1;
    if ((uVar3 & 1) != 0) goto code_r0x02abd390;
    unaff_x19 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
    System_ArgumentException___ctor
              (unaff_x19,_str_Coroutines_can_only_be_stopped_on_a_MonoBehaviour,0);
  }
  func_0x010eebec(unaff_x19,0,_mm_Method_UnityEngine_MonoBehaviour_StartCoroutine__);
  unaff_x30 = UnityEngine_MonoBehaviour__StartCoroutineManaged2;
  auVar13 = func_0x0112148c();
  lVar9 = auVar13._8_8_;
  register0x00000008 = (BADSPACEBASE *)&stack0xffffffffffffffd0;
  lVar4 = auVar13._0_8_;
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
  lVar4 = (*pcRam0000000004a040b0)(lVar4,lVar9);
  return lVar4;
}


