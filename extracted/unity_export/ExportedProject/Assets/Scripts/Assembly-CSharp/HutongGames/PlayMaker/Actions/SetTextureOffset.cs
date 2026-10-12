using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetTextureOffset : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		[RequiredField]
		public FsmString namedTexture;

		[RequiredField]
		public FsmFloat offsetX;

		[RequiredField]
		public FsmFloat offsetY;

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

		private void DoSetTextureOffset()
		{
		}
	}
}
