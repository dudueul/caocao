// [RE] Rebuild-only Town glue (the original Town flow ran through
// GameFlowController/WorldMapControlFlow — restored in later milestones).
// Responsibilities now: on Town load, refresh user state from the server
// (REST /user/getUserData) so the hub UI has live data.
using UnityEngine;

public class TownController : MonoBehaviour
{
	private void Start()
	{
		if (MyUserData.hasInstance)
			MyUserData.instance.RefreshUserData();
		else
			Debug.LogWarning("[Town] MyUserData missing — SessionBoot has not run");
	}
}
