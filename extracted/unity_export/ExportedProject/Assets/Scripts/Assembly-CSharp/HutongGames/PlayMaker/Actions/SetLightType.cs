using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetLightType : ComponentAction<Light>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEnum lightType;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetLightType()
		{
		}
	}
}
