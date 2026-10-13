// name: Multiplay.MultiplayManager$$OnConfirmPopup
// entry: 013af224

/* WARNING: Removing unreachable block (ram,0x01092280) */
/* WARNING: Removing unreachable block (ram,0x010922a0) */
/* WARNING: Removing unreachable block (ram,0x010922a4) */
/* WARNING: Removing unreachable block (ram,0x01092354) */
/* WARNING: Removing unreachable block (ram,0x0109235c) */
/* WARNING: Removing unreachable block (ram,0x01092388) */
/* WARNING: Removing unreachable block (ram,0x010922c8) */
/* WARNING: Removing unreachable block (ram,0x010922d4) */
/* WARNING: Removing unreachable block (ram,0x010922d8) */
/* WARNING: Removing unreachable block (ram,0x010922e4) */
/* WARNING: Removing unreachable block (ram,0x010922ec) */
/* WARNING: Removing unreachable block (ram,0x010923a8) */
/* WARNING: Removing unreachable block (ram,0x01092418) */
/* WARNING: Removing unreachable block (ram,0x010923b0) */
/* WARNING: Removing unreachable block (ram,0x010923c8) */
/* WARNING: Removing unreachable block (ram,0x010923d4) */
/* WARNING: Removing unreachable block (ram,0x0109245c) */
/* WARNING: Removing unreachable block (ram,0x010923e0) */
/* WARNING: Removing unreachable block (ram,0x010923f0) */
/* WARNING: Removing unreachable block (ram,0x0109246c) */
/* WARNING: Removing unreachable block (ram,0x01092308) */
/* WARNING: Removing unreachable block (ram,0x01092404) */
/* WARNING: Removing unreachable block (ram,0x0109230c) */
/* WARNING: Removing unreachable block (ram,0x01092320) */
/* WARNING: Removing unreachable block (ram,0x0109232c) */
/* WARNING: Removing unreachable block (ram,0x0109242c) */
/* WARNING: Removing unreachable block (ram,0x01092338) */
/* WARNING: Removing unreachable block (ram,0x01092348) */
/* WARNING: Removing unreachable block (ram,0x0109243c) */
/* WARNING: Removing unreachable block (ram,0x01092440) */
/* WARNING: Removing unreachable block (ram,0x01092380) */
/* WARNING: Removing unreachable block (ram,0x0109238c) */
/* WARNING: Removing unreachable block (ram,0x01092394) */
/* WARNING: Removing unreachable block (ram,0x02016118) */
/* WARNING: Removing unreachable block (ram,0x02016134) */
/* WARNING: Removing unreachable block (ram,0x02016150) */
/* WARNING: Removing unreachable block (ram,0x0201617c) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnConfirmPopup(void)

{
  ulong uVar1;
  long lVar2;
  
  if ((bRam00000000049f7b41 & 1) == 0) {
    func_0x010c65d4(0x8a4a);
    bRam00000000049f7b41 = 1;
  }
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = Singleton<WorldMapControlFlow>__get_HasInstance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_HasInstance__);
  if ((uVar1 & 1) != 0) {
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar2 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    uVar1 = CaocaoLoadIndicator__get_isLoading(lVar2,0);
    if ((uVar1 & 1) != 0) {
      if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar2 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
      if (lVar2 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam00000000049fe8cb & 1) == 0) {
        func_0x010c65d4(0x23fa,0,0);
        bRam00000000049fe8cb = 1;
      }
      if (*(char *)(lVar2 + 0x38) != '\0') {
        *(undefined1 *)(lVar2 + 0x19) = 1;
      }
      return;
    }
  }
  return;
}


