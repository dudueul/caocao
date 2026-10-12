using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetMaterialColor : ComponentAction<Renderer>
	{
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		public FsmMaterial material;

		public FsmString namedColor;

		[RequiredField]
		public FsmColor color;

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

		private void DoSetMaterialColor()
		{
		}
	}
}
