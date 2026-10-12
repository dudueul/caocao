using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetMaterialFloat : ComponentAction<Renderer>
	{
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		public FsmMaterial material;

		[RequiredField]
		public FsmString namedFloat;

		[RequiredField]
		public FsmFloat floatValue;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetMaterialFloat()
		{
		}
	}
}
