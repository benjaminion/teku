/*
 * Copyright 2021 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.teku.spec;

import java.io.IOException;
import java.util.List;
import tech.pegasys.teku.datastructures.state.Fork;
import tech.pegasys.teku.infrastructure.unsigned.UInt64;
import tech.pegasys.teku.spec.constants.SpecConstants;
import tech.pegasys.teku.spec.constants.SpecConstantsReader;
import tech.pegasys.teku.ssz.SSZTypes.Bytes4;

public class StubSpecProvider {

  public static SpecProvider create() {
    final Bytes4 genesisFork = Bytes4.fromHexString("0x00000000");
    final SpecConfiguration config = SpecConfiguration.builder().constants(loadConstants()).build();
    final ForkManifest forkManifest =
        ForkManifest.create(List.of(new Fork(genesisFork, genesisFork, UInt64.ZERO)));
    return SpecProvider.create(config, forkManifest);
  }

  private static SpecConstants loadConstants() {
    final SpecConstantsReader reader = new SpecConstantsReader();
    try {
      return reader.read(SpecConstants.class.getResourceAsStream("minimal.yaml"));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
