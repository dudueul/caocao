// name: Chat.Chatting$$.ctor
// entry: 01d27718

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined1  [16] Chat_Chatting___ctor(ulong param_1)

{
  long lVar1;
  undefined8 uVar2;
  undefined1 auVar3 [16];
  
  if ((bRam00000000049fcef2 & 1) == 0) {
    func_0x010c65d4(0x25ff);
    bRam00000000049fcef2 = 1;
  }
  lVar1 = func_0x010fdda8(
                         _class_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_TypeInfo
                         );
  System_Collections_Generic_Dictionary<string,_EnumData_Locale>___ctor
            (lVar1,_mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>__ctor__
            );
  if (lVar1 == 0) {
    func_0x010eec64(0);
    System_Collections_Generic_Dictionary<string,_EnumData_Locale>__Add
              (0,_str_English,5,
               _mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_Add__);
    func_0x010eec64(0);
  }
  else {
    System_Collections_Generic_Dictionary<string,_EnumData_Locale>__Add
              (lVar1,_str_English,5,
               _mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_Add__);
  }
  System_Collections_Generic_Dictionary<string,_EnumData_Locale>__Add
            (lVar1,_str_繁體中文,1,
             _mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_Add__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
    System_Collections_Generic_Dictionary<string,_EnumData_Locale>__Add
              (0,_str_한국어,4,
               _mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_Add__);
    func_0x010eec64(0);
  }
  else {
    System_Collections_Generic_Dictionary<string,_EnumData_Locale>__Add
              (lVar1,_str_한국어,4,
               _mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_Add__);
  }
  System_Collections_Generic_Dictionary<string,_EnumData_Locale>__Add
            (lVar1,_str_日本語,3,
             _mm_Method_System_Collections_Generic_Dictionary<string,_EnumData_Locale>_Add__);
  *(long *)(param_1 + 0x18) = lVar1;
  uVar2 = func_0x010fdda8(_class_System_Collections_Generic_List<int>_TypeInfo);
  System_Collections_Generic_List<int>___ctor
            (uVar2,_mm_Method_System_Collections_Generic_List<int>__ctor__);
  *(undefined8 *)(param_1 + 0x90) = uVar2;
  if (((*(byte *)(_class_Singleton<Chatting>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<Chatting>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if (param_1 == 0) {
    func_0x010eec64(0,_mm_Method_Singleton<Chatting>__ctor__);
  }
  if ((bRam0000000004a05620 & 1) == 0) {
    func_0x010c65d4(0x2e83,0);
    bRam0000000004a05620 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  auVar3._8_8_ = 0;
  auVar3._0_8_ = param_1;
  return auVar3;
}


