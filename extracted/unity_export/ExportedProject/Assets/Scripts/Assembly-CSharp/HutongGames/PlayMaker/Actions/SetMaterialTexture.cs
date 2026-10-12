using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetMaterialTexture : ComponentAction<Renderer>
	{
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		public FsmMaterial material;

		public FsmString namedTexture;

		public FsmTexture texture;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetMaterialTexture()
		{
		}
	}
}
