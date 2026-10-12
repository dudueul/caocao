using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetLightFlare : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public Flare lightFlare;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetLightRange()
		{
		}
	}
}
