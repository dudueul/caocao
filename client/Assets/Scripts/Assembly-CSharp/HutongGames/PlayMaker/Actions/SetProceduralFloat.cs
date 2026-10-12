namespace HutongGames.PlayMaker.Actions
{
	public class SetProceduralFloat : FsmStateAction
	{
		[RequiredField]
		public FsmMaterial substanceMaterial;

		[RequiredField]
		public FsmString floatProperty;

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

		private void DoSetProceduralFloat()
		{
		}
	}
}
