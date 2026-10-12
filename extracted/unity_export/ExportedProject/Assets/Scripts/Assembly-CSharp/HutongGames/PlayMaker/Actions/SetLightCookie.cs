using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetLightCookie : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmTexture lightCookie;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetLightCookie()
		{
		}
	}
}
