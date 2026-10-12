namespace HutongGames.PlayMaker.Actions
{
	public class SetProceduralBoolean : FsmStateAction
	{
		[RequiredField]
		public FsmMaterial substanceMaterial;

		[RequiredField]
		public FsmString boolProperty;

		[RequiredField]
		public FsmBool boolValue;

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
