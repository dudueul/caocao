// name: ClickChatting$$OnClick
// entry: 01d409e0

/* WARNING: Removing unreachable block (ram,0x01ff87ac) */
/* WARNING: Removing unreachable block (ram,0x01ff87b0) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void ClickChatting__OnClick(void)

{
  uint uVar1;
  long lVar2;
  ulong uVar3;
  undefined8 uStack_48;
  undefined8 uStack_40;
  undefined8 uStack_38;
  
  if ((bRam00000000049fe762 & 1) == 0) {
    func_0x010c65d4(0xd3a1,0);
    bRam00000000049fe762 = 1;
  }
  uStack_40 = 0;
  uStack_38 = 0;
  uStack_48 = 0;
  if (**(long **)(_class_TopBtnChat_TypeInfo + 0xb8) != 0) {
    System_Collections_Generic_List<object>__GetEnumerator
              (&uStack_48,**(long **)(_class_TopBtnChat_TypeInfo + 0xb8),
               _mm_Method_System_Collections_Generic_List<TopBtnChat>_GetEnumerator__);
    do {
      do {
        uVar1 = func_0x027e6418(&uStack_48,
                                _mm_Method_System_Collections_Generic_List_Enumerator<TopBtnChat>_MoveNext__
                               );
        if ((uVar1 & 1) == 0) goto code_r0x01ff8778;
        lVar2 = func_0x027e64f4(&uStack_48,
                                _mm_Method_System_Collections_Generic_List_Enumerator<TopBtnChat>_get_Current__
                               );
        if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar3 = UnityEngine_Object__op_Inequality(0,lVar2,0,0);
      } while ((uVar3 & 1) == 0);
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = UnityEngine_Behaviour__get_enabled(lVar2,0);
    } while ((uVar3 & 1) == 0);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    ChatButton__OnClick(lVar2,0);
code_r0x01ff8778:
    func_0x027e6294(&uStack_48,
                    _mm_Method_System_Collections_Generic_List_Enumerator<TopBtnChat>_Dispose__);
  }
  return;
}


