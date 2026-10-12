// name: System.Security.Cryptography.DecryptedInfo$$SetCipherInfo
// entry: 01b143fc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void System_Security_Cryptography_DecryptedInfo__SetCipherInfo(long param_1)

{
  long *plVar1;
  
  if ((bRam00000000049fbd3a & 1) == 0) {
    func_0x010c65d4(0x360d);
    bRam00000000049fbd3a = 1;
  }
  plVar1 = (long *)func_0x010fdda8(_class_System_Security_Cryptography_RijndaelManaged_TypeInfo);
  System_Security_Cryptography_RijndaelManaged___ctor(plVar1,0);
  *(long **)(param_1 + 0x10) = plVar1;
  if (plVar1 == (long *)0x0) {
    func_0x010eec64(0);
  }
  (**(code **)(*plVar1 + 0x260))(plVar1,1,*(undefined8 *)(*plVar1 + 0x268));
  plVar1 = *(long **)(param_1 + 0x10);
  if (plVar1 == (long *)0x0) {
    func_0x010eec64(0);
  }
                    /* WARNING: Could not recover jumptable at 0x01b144a0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(*plVar1 + 0x280))(plVar1,1,*(undefined8 *)(*plVar1 + 0x288));
  return;
}


