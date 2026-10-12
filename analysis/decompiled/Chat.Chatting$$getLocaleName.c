// name: Chat.Chatting$$getLocaleName
// entry: 01d27924

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 Chat_Chatting__getLocaleName(long param_1)

{
  int iVar1;
  uint uVar2;
  int iVar3;
  long lVar4;
  undefined8 uVar5;
  long lVar6;
  undefined1 auVar7 [16];
  undefined8 uStack_60;
  undefined8 uStack_58;
  undefined8 uStack_50;
  undefined8 uStack_48;
  undefined1 auStack_40 [16];
  
  if ((bRam00000000049fcef3 & 1) == 0) {
    func_0x010c65d4(0x260d);
    bRam00000000049fcef3 = 1;
  }
  auStack_40._0_8_ = 0;
  auStack_40._8_8_ = 0;
  uStack_50 = 0;
  uStack_48 = 0;
  uStack_60 = 0;
  uStack_58 = 0;
  lVar6 = *(long *)(param_1 + 0x48);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = *(int *)(lVar6 + 0x38);
  lVar6 = (long)(iVar1 + 2);
  if (4 < iVar1 - 1U) {
    lVar6 = 0;
  }
  if (((*(byte *)(_class_Singleton<Chatting>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<Chatting>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = Singleton<iTweenManager>__get_instance(0,_mm_Method_Singleton<Chatting>_get_instance__);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar4 = *(long *)(lVar4 + 0x18);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = *(int *)(&UNK_03997da0 + lVar6 * 4);
  System_Collections_Generic_Dictionary<object,_EnumData_Locale>__GetEnumerator
            (&uStack_60,lVar4,
             _mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_GetEnumerator__
            );
  do {
    uVar2 = func_0x01e0ef14(&uStack_60,
                            _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_EnumData_Locale>_MoveNext__
                           );
    if ((uVar2 & 1) == 0) {
      func_0x01e0f388(&uStack_60,
                      _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_EnumData_Locale>_Dispose__
                     );
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      return **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    }
    auVar7 = func_0x01e0f0b8(&uStack_60,
                             _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_EnumData_Locale>_get_Current__
                            );
    auStack_40 = auVar7;
    iVar3 = func_0x025ab9e4(auStack_40,
                            _mm_Method_System_Collections_Generic_KeyValuePair<string,_EnumData_Locale>_get_Value__
                           );
  } while (iVar3 != iVar1);
  uVar5 = func_0x025ab9cc(auStack_40,
                          _mm_Method_System_Collections_Generic_KeyValuePair<string,_EnumData_Locale>_get_Key__
                         );
  func_0x01e0f388(&uStack_60,
                  _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_EnumData_Locale>_Dispose__
                 );
  return uVar5;
}


