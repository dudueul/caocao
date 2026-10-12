namespace HutongGames.PlayMaker.Actions
{
	public class RebuildTextures : FsmStateAction
	{
		[RequiredField]
		public FsmMaterial substanceMaterial;

		[RequiredField]
		public FsmBool immediately;

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

		private void DoRebuildTextures()
		{
		}
	}
}
